package css.flooring;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextWidth;
    EditText editTextLength;
    TextView textViewResult;
    Button button;

    //constant to determine which sub-activity returns
    private static final int CIS3334_REQUEST_CODE = 1001;
    //Create an ActivityResultLauncher to handle result from Result Activity
    private ActivityResultLauncher<Intent> launcher;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editTextWidth = findViewById(R.id.editTextWidth);
        editTextLength = findViewById(R.id.editTextLength);
        textViewResult = findViewById(R.id.textViewSecondActivityResult);
        setupButton();

        // Initialize the ActivityResultLauncher
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.d("CIS 3334", "Return from second activity");   // log button click for debugging using "CIS 3334" tag

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            double resultDouble = data.getDoubleExtra("NewFlooring", 0);
                            Log.d("CIS 3334", "NewFlooring = "+ resultDouble);   // log button click for debugging using "CIS 3334" tag
                            textViewResult.setText("Flooring needed: " + resultDouble);
                        }
                    }
                }
        );
    }



    private void setupButton() {
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the second activity
                double width = Double.parseDouble( editTextWidth.getText().toString() );
                double length = Double.parseDouble( editTextLength.getText().toString() );
                //int flooring = width*length;
                Intent secActIntent = new Intent(MainActivity.this, ResultsActivity.class);
                secActIntent.putExtra("MainWidth", width);
                secActIntent.putExtra("MainLength", length);
                //secActIntent.putExtra("MainFlooring", flooring);
                //startActivity(secActIntent);     // if no result is returned
                //startActivityForResult(secActIntent, CIS3334_REQUEST_CODE);
                launcher.launch(secActIntent);
            }
        });
    }
}