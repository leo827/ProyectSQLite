package apps.IrvinTheSenpai.MiStore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    //Views
    private FloatingActionButton addRecordBtn;

    //RecyclerView
    private RecyclerView recordsRv;

    //DB Helper
    private MyDbHelper dbHelper;

    //Action Bar
    ActionBar actionBar;

    // ordenar opciones
    String orderByNewest = Constants.C_ADDED_TIMESTAMP + " DESC";
    String orderByOldes = Constants.C_ADDED_TIMESTAMP + " ASC";
    String orderByTitleAsc = Constants.C_NAME + " ASC";
    String orderByTitleDesc = Constants.C_NAME + " DESC";


    String currentOrderByStatus = orderByNewest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Inicializar Vista
        addRecordBtn = findViewById(R.id.addRecordBtn);
        recordsRv = findViewById(R.id.recordsRv);
        //Inicializamos db helper Clase
        dbHelper = new MyDbHelper(this);

        //Inicializacion ActionBar
        actionBar = getSupportActionBar();
        actionBar.setTitle("Registros");

        // Cargando Registros
            loadRecords(orderByNewest);

        // Click para Iniciar a añadir y grabar en la activity
        addRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Iniciar la Activity
                Intent intent = new Intent(MainActivity.this,AgregarRegistroActivity.class);
                intent.putExtra("isEditMode", false);//desea establecer nuevos datos, set false
                startActivity(intent);
            }
        });


    }
    private void loadRecords(String orderBy) {

        currentOrderByStatus = orderBy;
        AdapterRecord adapterRecord = new AdapterRecord(MainActivity.this,
                dbHelper.getAllRecords(orderBy));



        recordsRv.setAdapter(adapterRecord);

        //Establecer el numero de Registros
        actionBar.setSubtitle("Total: " + dbHelper.getRecordsCount());
    }

    private void searchRecords(String query) {

        AdapterRecord adapterRecord = new AdapterRecord(MainActivity.this,
                dbHelper.searchRecords(query));

        recordsRv.setAdapter(adapterRecord);

    }

    @Override
    protected void onResume() {

        super.onResume();

        loadRecords(currentOrderByStatus);// Refresca o actualiza la lista de registros

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu
        getMenuInflater().inflate(R.menu.menu_main, menu);


        //searchView
        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // buscar cuando se hace clic en el botón de búsqueda en el teclado
                searchRecords(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // busca mientras escribes
                searchRecords(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //maneja elementos del menu

          int id  = item.getItemId();
          if (id == R.id.action_shor){

           sortOntionDialog();
          }else if (id==R.id.action_delete){
           // eliminar todos los datos
              dbHelper.deleteAllData();
              onResume();




          }
        return super.onOptionsItemSelected(item);
    }

    private void sortOntionDialog() {
        String[] options = {"titulo ascandente","titulos descendente","el mas nuevo","mas antiguo"};
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Ordenar por").setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                   if(which== 0);
                {
                    loadRecords(orderByTitleAsc);
                }
                    if(which== 1);
                {
                    loadRecords(orderByTitleDesc);
                }
                    if(which==2);
                {
                    loadRecords(orderByNewest);
                }
                    if(which==3);{
                        loadRecords(orderByOldes);
                    }
                }

            }).create().show();
    }
}



