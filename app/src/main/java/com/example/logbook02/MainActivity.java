package com.example.logbook02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    ArrayList<String> urlLink;
    Button add_button;
    Button previous;
    Button next;
    EditText add_link;
    int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlLink = new ArrayList<String>();
        urlLink.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg");
        urlLink.add("https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        urlLink.add("https://www.whatsappimages.in/wp-content/uploads/2021/06/HD-New-Beautiful-Unique-Profile-Images-Pictures.gif");
        urlLink.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZw1FUc00oniGs02EfOQXR2-ZTET6cjzjq2q31wmj3_I5nmSNOp9CQBBcZUVfhkoybGb0&usqp=CAU");


        add_link = findViewById(R.id.add_link);
        imageView = findViewById(R.id.imageView);
        next = findViewById(R.id.previous);
        previous = findViewById(R.id.next);

        previous.setOnClickListener(this::renderImageWhenOnclick);
        next.setOnClickListener(this::renderImageWhenOnclick);
        add_button = findViewById(R.id.add_button);
        add_link = findViewById(R.id.add_link);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageURl = add_link.getText().toString();

                if (imageURl.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Image URL !!!", Toast.LENGTH_SHORT).show();
                } else {
                    urlLink.add(imageURl);
                    add_link.setText("");
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void renderImageWhenOnclick(View view) {
        if (view == previous) {
            currentImage++;
            if (currentImage == urlLink.size()) {
                currentImage = 0;
            }
        } else {
            if (currentImage == 0) {
                currentImage = urlLink.size();
            }
            currentImage--;
        }
        loadImage(currentImage);
    }

    private void loadImage(int item) {
        Glide.with(MainActivity.this)
                .load(urlLink.get(item))
                .into(imageView);
    }
}