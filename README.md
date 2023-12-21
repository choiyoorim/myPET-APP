## 바텀 내비게이션 각자 뷰에 적용하는 법

현재 해당 파일에서는 ExampleActivity에 적용해 본 사례

```java
import com.google.android.material.bottomnavigation.BottomNavigationView; 
// 만들어둔 BottomNavigationView를 불러와서 사용해야 함
import com.google.android.material.navigation.NavigationBarView;

public class ExampleActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView; //클래스 객체 선언

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //각자 뷰에 연결된 xml 연결

        bottomNavigationView = findViewById(R.id.menu_bottom_navigation); //해당 아이디로 객체에 저장

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
	          @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){
								// 클릭한 아이템의 아이디에 따라 화면 변환이 일어나면 됨
                switch (menuItem.getItemId()){
                    case R.id.menu_main:
												// 예시로 MainActivity로 연결해본 경우임 각자마다 기본 설정 추가해주면 됨
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_mypage:
                        break;
                    case R.id.menu_calendar:
                        break;
                    case R.id.menu_setting:
                        break;
                }
                return true;
            }

        });
    }
}
```

XML 파일에 바텀 내비게이션 뷰를 추가한 사례

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ExampleActivity">

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/menu_bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_gravity="bottom"
        app:labelVisibilityMode="unlabeled"
        app:menu="@menu/main_menu_bottom"
        app:itemIconTint="@drawable/selector"/>

</RelativeLayout>
```

각자 뷰에 맞게 노란색 부분을 추가해주면됨
