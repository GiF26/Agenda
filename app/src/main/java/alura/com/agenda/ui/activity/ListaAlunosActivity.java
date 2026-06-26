package alura.com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import alura.com.agenda.R;
import alura.com.agenda.dao.AlunoDAO;
import alura.com.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        dao.salva(new Aluno("Giovanna", "111222845", "giovanna@gmail.com"));
        dao.salva(new Aluno("Julia", "111222845", "julia@gmail.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton btnNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novoAluno);
        btnNovoAluno.setOnClickListener(v ->{
            abreFormularioAlunoActivity();
        });
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    private void configuraLista() {
        ListView alunosList = findViewById(R.id.activity_lista_alunos_listview);

        final List<Aluno> alunos = dao.todos();

        alunosList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));

        alunosList.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = alunos.get(position);
            Intent goToFormActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
            goToFormActivity.putExtra("aluno", alunoEscolhido);
            startActivity(goToFormActivity);
        });
    }
}

