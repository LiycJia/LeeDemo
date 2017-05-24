package lee.leedemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlePhoneDemoActivity extends AppCompatActivity {

    @BindView(R.id.rcl_devices)
    RecyclerView rcl_devices;
    @BindView(R.id.tv_nums)
    TextView tv_nums;
    private List<BluetoothDevice> mList = new ArrayList();
    private BluetoothAdapter bluetoothAdapter;
    private CustomAdapter adapter;
    private boolean isFilter = true;
    private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";

    //开始扫描设备
    @OnClick(R.id.btn_scan)
    void scan() {
        Log.e("ble", "scan");
        mList.clear();
        new Thread(() -> bluetoothAdapter.startDiscovery()).start();
    }

    //停止扫描设备
    @OnClick(R.id.btn_stop)
    void stop() {
        Log.e("ble", "stop");
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
    }

    @OnClick(R.id.btn_sifilter)
    void filter() {
        isFilter = !isFilter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_demo);
        ButterKnife.bind(this);
        rcl_devices.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter();
        rcl_devices.setAdapter(adapter);

        //初始化BluetoothAdapter
        BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        //如果检测蓝牙没有开启  尝试开启蓝牙
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 0);
        }
        // 注册Receiver来获取蓝牙设备相关的结果
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);// 用BroadcastReceiver来取得搜索结果
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);//配对状态
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);//扫描状态
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        scan();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(BlePhoneDemoActivity.this)
                    .inflate(R.layout.item_ble_demo, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final BluetoothDevice device = mList.get(position);
            holder.tv_name.setText(device.getName() + "\n"
                    + device.getAddress() + "\n"
                    + device.getUuids());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                BluetoothSocket bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(SPP_UUID));
                                if (!bluetoothSocket.isConnected())
                                    connectDevices(device, bluetoothSocket);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_name)
            TextView tv_name;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    private void connectDevices(BluetoothDevice device, BluetoothSocket bluetoothSocket) {
        try {
            //配对
            if (device.getBondState() == BluetoothDevice.BOND_NONE) {
                Method creMethod = BluetoothDevice.class.getMethod("createBond");
                creMethod.invoke(device);
            }
            if (bluetoothSocket.isConnected())
                bluetoothSocket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("ble", intent.getAction());
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //发现设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (isFilter)
                    mList.add(device);
                Log.e("ble", mList.size() + "");
                tv_nums.setText("发现设备" + mList.size());
                adapter.notifyDataSetChanged();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //搜索完成
                tv_nums.setText("搜索完成：\n发现设备" + mList.size());
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_BONDING:
                        Log.e("ble", "正在配对");
                        break;
                    case BluetoothDevice.BOND_BONDED:
                        Log.e("ble", "配对完成");
                        break;
                    case BluetoothDevice.BOND_NONE:
                        Log.e("ble", "取消配对");
                        break;
                }
            }
        }
    };
}
