package com.example.catchco2ifyoucan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.catchco2ifyoucan.databinding.ActivityListBinding;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ActivityListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ListView listView = findViewById(R.id.listView);
        SingleAdapter adapter = new SingleAdapter();
        adapter.addItem(new SingleItem(1, "일회용컵 사용", 11));
        adapter.addItem(new SingleItem(2, "샤워 15분", 86));
        adapter.addItem(new SingleItem(3, "헤어드라이기 사용 5분", 43));
        adapter.addItem(new SingleItem(4, "화장실 1회", 76));
        adapter.addItem(new SingleItem(5, "세탁기 1시간", 791));
        adapter.addItem(new SingleItem(6, "노트북 사용 10시간", 258));
        adapter.addItem(new SingleItem(7, "TV 시청 2시간", 129));
        adapter.addItem(new SingleItem(8, "냉장고 24시간", 554));
        adapter.addItem(new SingleItem(9, "전기밥솥 사용 10시간 (보온 포함)", 752));
        adapter.addItem(new SingleItem(10, "사무실 형광등 10시간", 103));
        adapter.addItem(new SingleItem(11, "과자 1봉지(160g)", 250));
        adapter.addItem(new SingleItem(12, "쇠고기(320g)", 4390));
        adapter.addItem(new SingleItem(13, "곡물 320g", 180));
        adapter.addItem(new SingleItem(14, "오렌지주스(250ml)", 360));
        adapter.addItem(new SingleItem(15, "커피 아메리카노", 560));
        adapter.addItem(new SingleItem(16, "김치찌개", 487));
        adapter.addItem(new SingleItem(17, "제육볶음", 460));
        adapter.addItem(new SingleItem(18, "커피 라떼", 340));
        adapter.addItem(new SingleItem(19, "잡곡밥", 285));
        adapter.addItem(new SingleItem(20, "잡채", 223));
        adapter.addItem(new SingleItem(21, "쌀밥", 115));
        adapter.addItem(new SingleItem(22, "밀가루", 115));
        adapter.addItem(new SingleItem(23, "사과", 96));
        adapter.addItem(new SingleItem(24, "토마토", 78));
        adapter.addItem(new SingleItem(25, "콩조림", 68));
        adapter.addItem(new SingleItem(26, "배추김치", 30.4));
        adapter.addItem(new SingleItem(27, "깍두기", 27.2));
        adapter.addItem(new SingleItem(28, "멸치조림", 12));
        adapter.addItem(new SingleItem(29, "설탕", 7.4));
        adapter.addItem(new SingleItem(30, "세탁기(한번 사용)", 364.4));
        adapter.addItem(new SingleItem(31, "랩", 202.5));
        adapter.addItem(new SingleItem(32, "샤워젤", 28.2));
        adapter.addItem(new SingleItem(33, "샴푸(하루 한번 사용)", 17.3));
        adapter.addItem(new SingleItem(34, "뉴에코백", 14.2));
        adapter.addItem(new SingleItem(35, "화장지", 9.4));
        adapter.addItem(new SingleItem(36, "비누", 8.2));
        adapter.addItem(new SingleItem(37, "주방세제", 7.4));
        adapter.addItem(new SingleItem(38, "치약(하루 2번 사용)", 4.2));
        adapter.addItem(new SingleItem(39, "세제", 0.03));
        adapter.addItem(new SingleItem(40, "빨아쓰는 키친타올", 0.01));
        adapter.addItem(new SingleItem(41, "햇반(210g)", 275));
        adapter.addItem(new SingleItem(42, "코카콜라(500ml)", 168));

        adapter.addItem(new SingleItem(43, "여객기", 0.14));
        adapter.addItem(new SingleItem(44, "SUV 자동차", 0.17));
        adapter.addItem(new SingleItem(45, "중형차", 0.13));
        adapter.addItem(new SingleItem(46, "철도", 0.08));
        adapter.addItem(new SingleItem(47, "하이브리드차", 0.08));
        adapter.addItem(new SingleItem(48, "고속버스", 0.05));

        listView.setAdapter(adapter);
    }

    class SingleAdapter extends BaseAdapter {
        //데이터가 들어가있지 않고, 어떻게 담을지만 정의해뒀다.
        ArrayList<SingleItem> items = new ArrayList<SingleItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingleItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // 어댑터가 데이터를 관리하고 뷰도 만듬
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingleItemView singleItemView = null;
            // 코드를 재사용할 수 있도록
            if(convertView == null) {
                singleItemView = new SingleItemView(getApplicationContext());

                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.single_item_list, null);
            } else {
                singleItemView = (SingleItemView)convertView;
            }
            SingleItem item = items.get(position);
            singleItemView.setJob(item.getJob());
            singleItemView.setCO2(item.getCO2());

            Button button = (Button) singleItemView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("Job", item.getJob());
                    intent.putExtra("CO2", item.getCO2());
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                    finish();
                }
            });

            return singleItemView;
        }
    }
}