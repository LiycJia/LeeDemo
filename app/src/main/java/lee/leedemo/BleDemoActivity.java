package lee.leedemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BleDemoActivity extends AppCompatActivity {

    @BindView(R.id.rcl_devices)
    RecyclerView rcl_devices;
    @BindView(R.id.tv_nums)
    TextView tv_nums;
    private List<ScanResult> mList = new ArrayList();
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private MyCallBack mBleScanCallback;
    private CustomAdapter adapter;
    private boolean isFilter = false;

    //开始扫描设备
    @OnClick(R.id.btn_scan)
    void scan() {
        Log.e("ble", "scan");
        bluetoothLeScanner.startScan(mBleScanCallback = new MyCallBack());
    }

    //停止扫描设备
    @OnClick(R.id.btn_stop)
    void stop() {
        Log.e("ble", "stop");
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner.stopScan(mBleScanCallback);
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
        //5。0以上扫描设备用Scanner
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        //如果检测蓝牙没有开启  尝试开启蓝牙
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        scan();
    }

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(BleDemoActivity.this)
                    .inflate(R.layout.item_ble_demo, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            BluetoothDevice device = mList.get(position).getDevice();
            holder.tv_name.setText(device.getName() + "\n"
                    + device.getAddress() + "\n"
                    + device.getUuids());
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

    private class MyCallBack extends ScanCallback {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if (null != result.getDevice().getName()) {
                mList.add(result);
            } else if (isFilter) {
                mList.add(result);
            }
            Log.e("ble", mList.size() + "");
            tv_nums.setText("发现设备" + mList.size());
            adapter.notifyDataSetChanged();
        }
    }
}
