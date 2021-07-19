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
import java.time.LocalDate;
import java.util.Collections;
import java.util.ResourceBundle;

import controllers.Facade;
import dados.RepositorioGrupo;
import dados.RepositorioPessoa;

public class SorteioController implements Initializable{

    Facade fachada = Facade.getInstance();

    @FXML
    private ComboBox<Grupo> comboBox;

    @FXML
    private ComboBox<Grupo> comboBoxGrupo;

    ObservableList<Grupo> observableListGrupo = FXCollections.observableArrayList(fachada.listarGrupos());

    @FXML
    private ComboBox<Pessoa> comboBoxPessoa;

    ObservableList<Pessoa> observableListPessoa = FXCollections.observableArrayList(fachada.listarPessoas());

    @FXML
    private PasswordField txtSenha;

    @FXML
    void consultar(ActionEvent event) {

        if (!comboBoxGrupo.getSelectionModel().getSelectedItem().isSorteado()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Grupo não sorteado");
            alert.setContentText("Ainda não foi realizado o sorteio desse grupo");
            alert.showAndWait();
        } else {
            if (!txtSenha.getText().equals(comboBoxPessoa.getSelectionModel().getSelectedItem().getSenha())){
                System.out.println("Senha incorreta");
            } else {

                Pessoa pessoa = fachada.consultarAmigoSecreto(comboBoxGrupo.getSelectionModel().getSelectedItem(), comboBoxPessoa.getSelectionModel().getSelectedItem(), txtSenha.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Amigo secreto");
                alert.setHeaderText("Mensagem");
                alert.setContentText("O amigo secreto de " + comboBoxPessoa.getSelectionModel().getSelectedItem().getApelido() + " no grupo " + comboBoxGrupo.getSelectionModel().getSelectedItem().getNome() + " é " + pessoa.getApelido());
                alert.showAndWait();
            }
        }

        atualizar();
    }

    @FXML
    void sortear(ActionEvent event) {

        int codigoMensagem = fachada.sortear(comboBox.getSelectionModel().getSelectedItem());

        if (codigoMensagem == 1){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Grupo sorteado");
            alert.setContentText("O grupo foi sorteado com sucesso");
            alert.showAndWait();

        }
        else if (codigoMensagem == 2){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Selecione um grupo");
            alert.setContentText("Nenhum grupo foi selecionado");
            alert.showAndWait();

        } else if (codigoMensagem == 3){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro no sorteio");
            alert.setContentText("O grupo selecionado já foi sorteado");
            alert.showAndWait();

        } else if (codigoMensagem == 4) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro no sorteio");
            alert.setContentText("Não é possível realizar o sorteio na data prevista ou posterior");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro não esperado");
            alert.setContentText("Contatar o programador para saber mais");
            alert.showAndWait();

        }

        atualizar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizar();
    }

    public void atualizar(){
        comboBox.setItems(observableListGrupo);

        comboBoxGrupo.setItems(observableListGrupo);

        comboBoxPessoa.setItems(observableListPessoa);
    }
}
