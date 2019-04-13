package com.spala.invokelambda;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    String COGNITO_IDENTITY_POOL = "us-west-2:da60c3fb-957c-435a-a52b-4768e0f75fcb";

    private Button mSendEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendEventButton = (Button) findViewById(R.id.send_event_button);

        mSendEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

    }


    private void init() {
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),    /* get the context for the application */
                COGNITO_IDENTITY_POOL,    /* Identity Pool ID */
                Regions.US_WEST_2           /* Region for your identity pool--US_EAST_1 or EU_WEST_1*/
        );


        LambdaInvokerFactory factory = new LambdaInvokerFactory(
                getApplicationContext(),
                Regions.US_WEST_2,
                credentialsProvider);


        final MyInterface myInterface = factory.build(MyInterface.class);

        Info info = new Info("John", "Doe");

// The Lambda function invocation results in a network call
// Make sure it is not called from the main thread
        new AsyncTask<Info, Void, String>() {
            @Override
            protected String doInBackground(Info... params) {
                // invoke "echo" method. In case it fails, it will throw a
                // LambdaFunctionException.
                try {
                    return myInterface.FirebaseServer(params[0]);
                } catch (LambdaFunctionException lfe) {
                    Log.e(TAG, "Failed to invoke echo", lfe);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                if (result == null) {
                    return;
                }

                // Do a toast
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        }.execute(info);

    }
}
