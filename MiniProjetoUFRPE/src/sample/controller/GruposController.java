package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.Grupo;
import model.Pessoa;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.Facade;
import dados.IRepositorioGrupo;
import dados.IRepositorioPessoa;
import dados.RepositorioGrupo;
import dados.RepositorioPessoa;

public class GruposController implements Initializable{

    Facade fachada = Facade.getInstance();

    @FXML
    private TextField txtNomeGrupo;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView<Pessoa> listaTodasAsPessoas;

    private ObservableList<Pessoa> observableListTodas = FXCollections.observableArrayList(fachada.listarPessoas());


    @FXML
    private ListView<Pessoa> listaPessoasNoGrupo;

    private ObservableList<Pessoa> observableListPessoaGrupo;


    @FXML
    private ComboBox<Grupo> comboBox;

    private ObservableList<Grupo> observableListGrupo = FXCollections.observableArrayList(fachada.listarGrupos());


    @FXML
    void adicionar(ActionEvent event) {
        Pessoa p = listaTodasAsPessoas.getSelectionModel().getSelectedItem();
        Grupo g = comboBox.getSelectionModel().getSelectedItem();

        int codigoMensagem = fachada.adicionarPessoaGrupo(p, g);

        if(codigoMensagem == 1){
            atualizarListas();
        }
        else if (codigoMensagem == 2){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Adição inválida");
            alert.setContentText("Essa pessoa jÃ¡ foi adicionada ao grupo");
            alert.showAndWait();

        }
        else if (codigoMensagem == 3){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione uma pessoa");
            alert.setContentText("Nenhuma pessoa foi selecionada");
            alert.showAndWait();

        }
        else if (codigoMensagem == 4){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione um grupo");
            alert.setContentText("Nenhum grupo foi selecionado");
            alert.showAndWait();

        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro não esperado");
            alert.setContentText("Contatar o programador para saber mais");
            alert.showAndWait();

        }

    }

    @FXML
    void remover(ActionEvent event) {
        Pessoa p = listaPessoasNoGrupo.getSelectionModel().getSelectedItem();
        Grupo g = comboBox.getSelectionModel().getSelectedItem();

        fachada.removerPessoaGrupo(p, g);

        atualizarListas();
    }

    @FXML
    void limpar(ActionEvent event) {
        txtNomeGrupo.setText("");
        datePicker.setValue(null);
    }

    @FXML
    void salvar(ActionEvent event) {

        int codigoMensagem = fachada.salvarGrupo(txtNomeGrupo.getText(), datePicker.getValue());

        observableListGrupo = FXCollections.observableArrayList(fachada.listarGrupos());
        atualizarListas();

        if (codigoMensagem == 1) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Sucesso!");
            alert.setContentText(txtNomeGrupo.getText() + " foi registrado com sucesso com a data " + datePicker.getValue());
            alert.showAndWait();

        } else if (codigoMensagem == 2){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Nenhum campo foi preenchido");
            alert.showAndWait();

        } else if (codigoMensagem == 3) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Data não foi selecionada");
            alert.showAndWait();

        } else if (codigoMensagem == 4){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Campo Nome não foi preenchido");
            alert.showAndWait();

        } else if (codigoMensagem == 5){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Grupo já existe");
            alert.setContentText("Já foi registrado um grupo com esse nome");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro não esperado");
            alert.setContentText("Contatar o programador para saber mais");
            alert.showAndWait();

        }

    }

    @FXML
    void selecionarComboBox(ActionEvent event) {
        atualizarListas();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizarListas();
    }

    public void atualizarListas(){

        listaTodasAsPessoas.setItems(observableListTodas);

        comboBox.setItems(observableListGrupo);

        observableListPessoaGrupo = FXCollections.observableArrayList();

        observableListPessoaGrupo = fachada.listarPessoasGrupo(comboBox.getSelectionModel().getSelectedItem());

        listaPessoasNoGrupo.setItems(observableListPessoaGrupo);
    }
}