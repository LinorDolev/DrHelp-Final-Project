package com.example.shiran.drhelp.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.shiran.drhelp.R;
import com.example.shiran.drhelp.entities.User;
import com.example.shiran.drhelp.services.FirebaseUserService;
import com.example.shiran.drhelp.services.UserService;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private UserService userService;
    private List<User> userList;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts);
        userService = FirebaseUserService.getInstance(getApplicationContext());
        recyclerView = findViewById(R.id.recycler_view);

        Gson gson = new Gson();

        Intent intent = getIntent();
        String usersListJSON = intent.getStringExtra("usersList");
        userList = Arrays.asList(gson.fromJson(usersListJSON, User[].class));
        showContactList();
    }

    private void showContactList() {
        userAdapter = new UserAdapter(this, userList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);

        Log.d("all users", userList.toString());
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

         @Override
         public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
             int position = parent.getChildAdapterPosition(view);
             int column = position % spanCount;

             if (includeEdge) {
                 outRect.left = spacing - column * spacing / spanCount;
                 outRect.right = (column + 1) * spacing / spanCount;

                 if (position < spanCount) { // top edge
                     outRect.top = spacing;
                 }
                 outRect.bottom = spacing; // item bottom
             } else {
                 outRect.left = column * spacing / spanCount;
                 outRect.right = spacing - (column + 1) * spacing / spanCount;
                 if (position >= spanCount) {
                     outRect.top = spacing; // item top
                 }
             }
         }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
