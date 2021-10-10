package bans.qaadhar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import bans.qaadhar.databinding.ActivityMainBinding;
import bans.qaadhar.models.AadharCard;
import bans.qaadhar.ui.AadharViewActivity;
import bans.qaadhar.ui.DatabaseActivity;
import bans.qaadhar.util.Keys;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.scan.setOnClickListener(v -> {
            if (checkCameraPermission()) {
                new IntentIntegrator(this).initiateScan();
            }
        });

        binding.database.setOnClickListener(v -> {
            Intent intent = new Intent(this, DatabaseActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Function to check if user has granted access to camera
     *
     * @return boolean
     */
    public boolean checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 47);
            return false;
        }
        return true;
    }


    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                final String scannedData = result.getContents();
                Log.e("Scanned Data", scannedData);
                parse_data(scannedData);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void parse_data(String scanData) {
        XmlPullParserFactory pullParserFactory;
        AadharCard card = new AadharCard();
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(scanData));
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && Keys._DATA.equals(parser.getName())) {
                    String uid = parser.getAttributeValue(null, Keys._UID);
                    String name = parser.getAttributeValue(null, Keys._NAME);
                    String gender = parser.getAttributeValue(null, Keys._GENDER);
                    String dob = parser.getAttributeValue(null, Keys._DOB);
                    String co = parser.getAttributeValue(null, Keys._CO);
                    String vtc = parser.getAttributeValue(null, Keys._VTC);
                    String po = parser.getAttributeValue(null, Keys._PO);
                    String dist = parser.getAttributeValue(null, Keys._DIST);
                    String state = parser.getAttributeValue(null, Keys._STATE);
                    String pc = parser.getAttributeValue(null, Keys._PC);

                    card.setUuid(uid);
                    card.setName(name);
                    card.setGender(gender);
                    card.setDateOfBirth(dob);
                    card.setCareOf(co);
                    card.setVtc(vtc);
                    card.setPostOffice(po);
                    card.setDistrict(dist);
                    card.setState(state);
                    card.setPinCode(pc);
                }
                eventType = parser.next();
            }
            showScannedResults(card);
        } catch (XmlPullParserException e) {
            showMessage("Error in processing QR-Code");
            e.printStackTrace();
        } catch (IOException e) {
            showMessage(e.toString());
            e.printStackTrace();
        }
    }


    private void showScannedResults(AadharCard data) {
        Intent intent = new Intent(this, AadharViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("aadhar", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void showMessage(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

}