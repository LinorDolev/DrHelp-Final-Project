package com.example.shiran.drhelp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiran.drhelp.R;
import com.example.shiran.drhelp.entities.User;

import com.example.shiran.drhelp.services.FirebaseNotificationService;
import com.example.shiran.drhelp.services.FirebaseUserService;
import com.example.shiran.drhelp.services.NotificationService;
import com.example.shiran.drhelp.services.UserService;
import com.example.shiran.drhelp.services.observers.NotificationServiceObserver;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>  implements NotificationServiceObserver{


    private List<User> userList;
    private Context context;
    private NotificationService notificationService;
    private UserService userService;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        this.notificationService = FirebaseNotificationService.getInstance(context);
        this.notificationService.subscribe(this);
        this.userService = FirebaseUserService.getInstance(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, int i) {
        User user = userList.get(i);
        userViewHolder.setUser(user);
    }

    private void onCallButtonPressed(View view, User toUser) {
        Intent to_videoChat_Intent = new Intent(this.context, VideoChatActivity.class);
        to_videoChat_Intent.putExtra("toUserName", toUser.getFirstName());
        to_videoChat_Intent.putExtra("toUserId", toUser.getId());
        this.context.startActivity(to_videoChat_Intent);
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    @Override
    public void onNotificationSucceed() {
        Toast.makeText(context, "Notification was sent", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNotificationFailed(Exception e) {
        Toast.makeText(context, "Error: notification was not sent :/", Toast.LENGTH_LONG).show();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private User user;

        TextView textView_userName;
        ImageView imageView_user;
        ImageView imageView_call;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_userName = itemView.findViewById(R.id.user_info_textView);
            imageView_user = itemView.findViewById(R.id.user_imageView);
            imageView_call = itemView.findViewById(R.id.call_imageView);
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
            this.textView_userName
                    .setText(user.getFirstName() + " " + user.getLastName());

            this.imageView_call.setOnClickListener(this::onCallClicked);
        }

        private void onCallClicked(View view){
            UserAdapter.this.notificationService.call(user);
            UserAdapter.this.userService.callToUser(user);
            Log.d("callDebug2:", "I'm calling to " + user.getFirstName() + " id: " + user.getId());

            UserAdapter.this.onCallButtonPressed(view, user);
        }
    }
}
