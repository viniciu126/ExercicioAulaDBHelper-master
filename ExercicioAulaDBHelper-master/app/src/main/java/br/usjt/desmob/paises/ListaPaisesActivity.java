package br.usjt.desmob.paises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Vinicius Maciel on 14/4/2018.
 * RA 816117960
 */

public class ListaPaisesActivity extends Activity {
    public static final String PAIS = "br.usjt.desmob.paises";
    Activity atividade;
    Pais[] paises;

    /**
     * Created by Vinicius Maciel on 14/4/2018.
     * RA 816117960
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paises);
        atividade = this;
        Intent intent = getIntent();

        paises = (Pais[]) intent.getSerializableExtra(MainActivity.PAISES);

        ListView listView = (ListView) findViewById(R.id.lista_paises);
        PaisAdapter adapter = new PaisAdapter(paises, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Created by Vinicius Maciel on 14/4/2018.
             * RA 816117960
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(atividade, DetalhePaisActivity.class);
                intent.putExtra(PAIS, paises[position]);

                startActivity(intent);

            }

        });
    }
}
