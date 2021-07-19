package sample.controller;

import controllers.Facade;
import dados.IRepositorioPresente;
import dados.RepositorioPresente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Presente;

public class PresentesController {

    Facade fachada = Facade.getInstance();

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtPreco;

    @FXML
    void limpar(ActionEvent event) {
        txtCategoria.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
    }


    @FXML
    void salvar(ActionEvent event) {

        int codigoMensagem = fachada.salvarPresente(txtDescricao.getText(), txtCategoria.getText(), txtPreco.getText());

        if (codigoMensagem == 1){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Sucesso!");
            alert.setContentText(txtDescricao.getText() + " foi registrado com sucesso com a categoria " + txtCategoria.getText() + " e preço de R$" + Float.parseFloat(txtPreco.getText()));
            alert.showAndWait();

        }
        else if (codigoMensagem == 2){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Campo Descrição não foi preenchido");
            alert.showAndWait();

        } else if (codigoMensagem == 3){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Campo Preço não foi preenchido");
            alert.showAndWait();

        } else if (codigoMensagem == 4){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Preencha todos os campos");
            alert.setContentText("Campo categoria não foi preenchido");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro não esperado");
            alert.setContentText("Contatar o programador para saber mais");
            alert.showAndWait();

        }

    }
}
