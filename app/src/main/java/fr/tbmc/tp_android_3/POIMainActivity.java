package fr.tbmc.tp_android_3;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class POIMainActivity extends ListActivity
{

    public static int REQ_CODE = 1;

    private ArrayAdapter<PointOfInterest> poiArrayAdapter;
    private List<PointOfInterest> poiList;
    private ListView poiListView;

    private long selectedId = -1;
    private int selectedPosition = -1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        PointOfInterest poi;
        int position = poiListView.getCheckedItemPosition();
        Intent intent;

        switch (item.getItemId()) {
            case R.id.add:
                /*poi = createPOI();
                poiArrayAdapter.add(poi);
                poiList.add(poi);*/
                intent = new Intent(this, POIAddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                // startActivity(intent);
                return true;
            case R.id.remove:
                if(position == AdapterView.INVALID_POSITION)
                    return true;
                poiList.remove(position);
                poiArrayAdapter.notifyDataSetChanged();
                return true;
            case R.id.showOnMap:
                if(position == AdapterView.INVALID_POSITION)
                    return true;
                poi = poiList.get(position);
                String uri = "http://maps.google.com/maps?q=" + poi.getLatitude() + "," + poi.getLongitude() + "(" + poi.getLabel() + ")";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showPOIOnGoogleMaps(PointOfInterest poi) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != RESULT_OK) {
            return;
        }
        System.out.println("test");
        if(requestCode == REQ_CODE) {
            Serializable obj =  data.getExtras().getSerializable(POIAddActivity.RETURN_STRING);
            if(obj instanceof PointOfInterest) {
                PointOfInterest poi = (PointOfInterest) obj;
                poiList.add(poi);
                poiArrayAdapter.notifyDataSetChanged();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poiList = new ArrayList<>();
        poiArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, poiList);
        poiListView = (ListView)findViewById(android.R.id.list);

        PointOfInterest poi1 = new PointOfInterest("label", "description", 0, 0);
        poiList.add(poi1);

        poiListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        poiListView.setAdapter(poiArrayAdapter);


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        menu.findItem(R.id.add).setVisible(true);

        boolean visible = poiListView.getCheckedItemPosition() != AdapterView.INVALID_POSITION;
        menu.findItem(R.id.remove).setVisible(visible);
        menu.findItem(R.id.showOnMap).setVisible(visible);

        return super.onPrepareOptionsMenu(menu);
    }

}
