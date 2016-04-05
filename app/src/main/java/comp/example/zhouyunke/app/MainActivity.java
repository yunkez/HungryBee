package comp.example.zhouyunke.app;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    ListView mDrawerList;
    ExpandableListView mOrderList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private Switch identitySwitch;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    ArrayList<OrderItem> mOrderItems = new ArrayList<OrderItem>();
    ArrayList<OrderDetail> mOrderDetail = new ArrayList<OrderDetail>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavItems.add(new NavItem("Orders", "Check your orders", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("Messages", "Check your messages", R.drawable.ic_launcher));
        mNavItems.add(new NavItem("Preferences", "Change your preferences", R.drawable.ic_launcher));

        mOrderDetail.add(new OrderDetail("Chicken Burger",1));
        mOrderDetail.add(new OrderDetail("Cheese Fries",2));

        mOrderItems.add(new OrderItem("KFC",mOrderDetail));
        mOrderItems.add(new OrderItem("Pizzahut",mOrderDetail));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        mOrderList = (ExpandableListView) findViewById(R.id.orderList);
        OrderListAdapter order_adapter = new OrderListAdapter(this, mOrderItems);
        mOrderList.setAdapter(order_adapter);

        identitySwitch = (Switch) findViewById(R.id.identitySwitch);
        identitySwitch.setChecked(true);
        identitySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "You are courier",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You are consumer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectItemFromDrawer(int position) {
//        Fragment fragment = new PreferencesFragment();
//
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.mainContent, fragment)
//                .commit();
//
//        mDrawerList.setItemChecked(position, true);
//        setTitle(mNavItems.get(position).mTitle);

        mDrawerLayout.closeDrawer(mDrawerPane);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


}

class NavItem {
    String mTitle;
    String mSubtitle;
    int mIcon;

    public NavItem(String title, String subtitle, int icon) {
        mTitle = title;
        mSubtitle = subtitle;
        mIcon = icon;
    }
}

class DrawerListAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<NavItem> mNavItems;

    public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
        mContext = context;
        mNavItems = navItems;
    }

    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);

        titleView.setText( mNavItems.get(position).mTitle );
        subtitleView.setText( mNavItems.get(position).mSubtitle );
        iconView.setImageResource(mNavItems.get(position).mIcon);

        return view;
    }
}

class OrderItem {
    String mRestaurant;
    ArrayList<OrderDetail> mOrder;
    int mStatus;

    public OrderItem(String restaurant, ArrayList<OrderDetail> order) {
        mRestaurant = restaurant;
        mOrder = order;
        mStatus = 0;
    }
}

class OrderDetail {
    String mFood;
    int mQuantity;

    public OrderDetail(String food, int quantity){
        mFood = food;
        mQuantity = quantity;
    }

}

class OrderListAdapter extends BaseExpandableListAdapter {

    Context mContext;
    ArrayList<OrderItem> orderItems;

    public OrderListAdapter(Context context, ArrayList<OrderItem> items) {
        mContext = context;
        orderItems = items;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.orderItems.get(groupPosition).mOrder.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        OrderDetail child = (OrderDetail)getChild(groupPosition, childPosition);
        String food = child.mFood;
        int q = child.mQuantity;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_item, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.order_name);
        TextView quantity = (TextView) convertView.findViewById(R.id.order_quantity);

        name.setText(food);
        quantity.setText(q+"");
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.orderItems.get(groupPosition).mOrder.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.orderItems.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.orderItems.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String restaurant_title = orderItems.get(groupPosition).mRestaurant;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.restaurant_name);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(restaurant_title);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}