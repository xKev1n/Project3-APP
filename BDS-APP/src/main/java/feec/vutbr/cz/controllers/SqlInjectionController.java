package feec.vutbr.cz.controllers;

import feec.vutbr.cz.api.SqlBasicView;
import feec.vutbr.cz.config.DataSourceConfig;
import feec.vutbr.cz.exceptions.DataAccessException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlInjectionController {

    private static final Logger logger = LoggerFactory.getLogger(SqlInjectionController.class);

    @FXML
    public Button runQueryButton;
    @FXML
    public Button refreshButton;
    @FXML
    public Button recreateTableButton;
    @FXML
    private TableColumn<SqlBasicView, String> col1TableColumn;
    @FXML
    private TableColumn<SqlBasicView, Double> col2TableColumn;
    @FXML
    private TableColumn<SqlBasicView, String> col3TableColumn;
    @FXML
    private TextField sqlInjectionTextField;
    @FXML
    private TableView<SqlBasicView> systemSqlTableView;



    @FXML
    private void initialize() {

        col1TableColumn.setCellValueFactory(new PropertyValueFactory<SqlBasicView, String>("col1"));
        col2TableColumn.setCellValueFactory(new PropertyValueFactory<SqlBasicView, Double>("col2"));
        col3TableColumn.setCellValueFactory(new PropertyValueFactory<SqlBasicView, String>("col3"));

        //ObservableList<SqlBasicView> observablePersonsList = initializeSqlData();
        //systemSqlTableView.setItems(observablePersonsList);


        //initializeTableViewSelection();

        logger.info("SqlInjectionController initialized");
    }


    private SqlBasicView mapToSqlBasicView(ResultSet rs) throws SQLException {
        SqlBasicView sqlBasicView = new SqlBasicView();
        sqlBasicView.setCol1(rs.getString("col1"));
        sqlBasicView.setCol2(rs.getDouble("col2"));
        sqlBasicView.setCol3(rs.getString("col3"));

        return sqlBasicView;
    }


    public List<SqlBasicView> getSqlBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT col1, col2, col3" +
                             " FROM lib_db.dummy");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<SqlBasicView> sqlBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                sqlBasicViews.add(mapToSqlBasicView(resultSet));
            }
            return sqlBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("SQL basic view could not be loaded.", e);
        }
    }


    private ObservableList<SqlBasicView> initializeSqlData() {
        List<SqlBasicView> rows = getSqlBasicView();
        return FXCollections.observableArrayList(rows);
    }


    public void handleSqlCreate(ActionEvent actionEvent) {

        String sql_query = "CREATE TABLE IF NOT EXISTS lib_db.dummy" +
                " (id SERIAL NOT NULL PRIMARY KEY, col1 VARCHAR NOT NULL, col2 REAL NOT NULL, col3 VARCHAR NOT NULL);";
        try (Connection connection = DataSourceConfig.getConnection()){
            connection.createStatement().executeUpdate(sql_query);
            handleSqlInsert();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void handleSqlInsert() throws SQLException {
        String sql_query = "INSERT INTO lib_db.dummy (col1, col2, col3) " +
                "VALUES ('Value1', 1000.0, 'Value1'), ('WP', 1000.0, 'Value2'), ('SUPA', 1500.0, 'SIKRIT'), ('SQL', 2000.0, 'INJECTION');";
        try (Connection connection = DataSourceConfig.getConnection()){
            connection.createStatement().executeUpdate(sql_query);
            tableCreatedConfirmationDialog();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void tableCreatedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Table Created Confirmation");
        alert.setHeaderText("Dummy table was successfully created.");

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void handleSqlQuery(ActionEvent actionEvent) {
        String parameter = sqlInjectionTextField.getText();

        String sql_query = "SELECT col1, col2, col3 FROM lib_db.dummy WHERE col1 = '"+parameter+"';";
        try (Connection connection = DataSourceConfig.getConnection()){

            List<SqlBasicView> sqlBasicViews = new ArrayList();
            ResultSet rs = connection.createStatement().executeQuery(sql_query);

            while (rs.next()) {
                sqlBasicViews.add(mapToSqlBasicView(rs));
            }

            systemSqlTableView.setItems(FXCollections.observableArrayList(sqlBasicViews));
            //return sqlBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("SQL query could not be performed.", e);
        }
    }

    public void handleRefresh(ActionEvent actionEvent) {
        systemSqlTableView.setItems(null);
        systemSqlTableView.refresh();
        systemSqlTableView.sort();
    }
}
