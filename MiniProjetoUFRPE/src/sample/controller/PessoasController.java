package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Pessoa;
import model.Presente;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.Facade;
import dados.RepositorioPessoa;
import dados.RepositorioPresente;

public class PessoasController implements Initializable {

    Facade fachada = Facade.getInstance();

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtApelido;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtConfSenha;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnSalvar;

    @FXML
    private ListView<Presente> listaTodosOsPresentes;

    ObservableList<Presente> observableListPresentes = FXCollections.observableArrayList(fachada.listarPresentes());

    @FXML
    private ListView<Presente> listaPresentesDaPessoa;

    ObservableList<Presente> observableListPresentePessoa = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Pessoa> comboBox;

    ObservableList<Pessoa> observableListPessoa = FXCollections.observableArrayList(fachada.listarPessoas());

    @FXML
    private Label nomePessoa;


    @FXML
    void limpar(ActionEvent event) {
        txtNome.setText("");
        txtApelido.setText("");
        txtSenha.setText("");
        txtConfSenha.setText("");
    }

    @FXML
    void salvar(ActionEvent event) {
        int codigoMensagem = fachada.salvarPessoa(txtNome.getText(), txtApelido.getText(), txtSenha.getText(), txtConfSenha.getText());

        if (codigoMensagem == 1){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Sucesso!");
            alert.setContentText(txtNome.getText() + " foi registrado com sucesso e possui o apelido " + txtApelido.getText());
            alert.showAndWait();

        }
        else if(codigoMensagem == 2){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Apelido já registrado");
            alert.setContentText("Já existe uma pessoa registrada com esse apelido");
            alert.showAndWait();

        }
        else if(codigoMensagem == 3){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Senha não confere");
            alert.setContentText("Senha digitada no campo 'Senha' é diferente do campo 'Confirmar Senha'");
            alert.showAndWait();

        } else if (codigoMensagem == 4){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Algum campo não foi preenchido");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro não esperado");
            alert.setContentText("Contatar o programador para saber mais");
            alert.showAndWait();

        }

        observableListPessoa = FXCollections.observableArrayList(fachada.listarPessoas());
        atualizar();
    }


    @FXML
    void removerPresente(ActionEvent event) {

        fachada.removerPresentePessoa(listaPresentesDaPessoa.getSelectionModel().getSelectedItem(), comboBox.getSelectionModel().getSelectedItem());

        atualizar();
    }

    @FXML
    void adicionarPresente(ActionEvent event) {
        Pessoa pessoa = comboBox.getSelectionModel().getSelectedItem();
        Presente presente = listaTodosOsPresentes.getSelectionModel().getSelectedItem();

        if (presente != null && pessoa != null){

            fachada.adicionarPresentePessoa(presente, pessoa);

            atualizar();

        }
        else if (pessoa == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione uma pessoa");
            alert.setContentText("Nenhuma pessoa foi selecionada");
            alert.showAndWait();
        }
        else if (presente == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione um presente");
            alert.setContentText("Nenhuma presente foi selecionado");
            alert.showAndWait();
        }
    }

    @FXML
    void selecionarComboBox(ActionEvent event) {
        atualizar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizar();
    }

    public void atualizar(){
        listaTodosOsPresentes.setItems(observableListPresentes);

        comboBox.setItems(observableListPessoa);

        observableListPresentePessoa = FXCollections.observableArrayList();

        listaPresentesDaPessoa.setItems(fachada.listarPresentesPessoa(comboBox.getSelectionModel().getSelectedItem()));

        if (comboBox.getSelectionModel().getSelectedItem() != null){
            nomePessoa.setText("Presentes de " + comboBox.getSelectionModel().getSelectedItem().getApelido());
        } else {
            nomePessoa.setText("Selecione uma pessoa");
        }
    }
}
