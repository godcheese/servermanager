package com.gioov.dao;

import com.gioov.common.Common;
import com.gioov.entity.ProjectEntity;
import com.gioov.util.JdbcUtil;
import com.gioov.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/17 16:25
 */

public class ProjectDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDao.class);

    private static Connection connection;

    static {
        connection = JdbcUtil.getInstance();
    }


    public static ProjectEntity getOne(Long id){
        ProjectEntity projectEntity = null;
        PreparedStatement preparedStatement;
        try {
            String sql = "select * from project where id = ? limit 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                projectEntity = new ProjectEntity();
                projectEntity.setId(resultSet.getLong("id"));
                projectEntity.setName(resultSet.getString("name"));
                projectEntity.setDeployPath(resultSet.getString("deploy_path"));
                projectEntity.setDatabaseType(Common.DatabaseType.valueOf(resultSet.getString("database_type")));
                projectEntity.setDatabaseHost(resultSet.getString("database_host"));
                projectEntity.setDatabasePort(resultSet.getString("database_port"));
                projectEntity.setDatabaseName(resultSet.getString("database_name"));
                projectEntity.setDatabaseUsername(resultSet.getString("database_username"));
                projectEntity.setDatabasePassword(resultSet.getString("database_password"));
                projectEntity.setBackupNameRule(resultSet.getString("backup_name_rule"));
                projectEntity.setBackupPath(resultSet.getString("backup_path"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectEntity;
    }

    public static ProjectEntity insertOne(ProjectEntity projectEntity) {
        PreparedStatement preparedStatement = null;
        Long rowId = null;
        try {
            connection.setAutoCommit(false);
            String sql = "insert into project(name, deploy_path, database_type, database_host, database_port, database_name, database_username, database_password, backup_name_rule, backup_path) values(?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, StringUtil.isNullReturnEmpty(projectEntity.getName()));
            preparedStatement.setString(2, StringUtil.isNullReturnEmpty(projectEntity.getDeployPath()));
            if(projectEntity.getDatabaseType()!=null) {
                preparedStatement.setString(3, StringUtil.isNullReturnEmpty(projectEntity.getDatabaseType().value));
            }
            preparedStatement.setString(4, StringUtil.isNullReturnEmpty(projectEntity.getDatabaseHost()));
            preparedStatement.setString(5, StringUtil.isNullReturnEmpty(projectEntity.getDatabasePort()));
            preparedStatement.setString(6, StringUtil.isNullReturnEmpty(projectEntity.getDatabaseName()));
            preparedStatement.setString(7, StringUtil.isNullReturnEmpty(projectEntity.getDatabaseUsername()));
            preparedStatement.setString(8, StringUtil.isNullReturnEmpty(projectEntity.getDatabasePassword()));
            preparedStatement.setString(9, StringUtil.isNullReturnEmpty(projectEntity.getBackupNameRule()));
            preparedStatement.setString(10, StringUtil.isNullReturnEmpty(projectEntity.getBackupPath()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                projectEntity.setId(resultSet.getLong(1));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            try {
                JdbcUtil.getInstance().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectEntity;
    }

    public static List<ProjectEntity> listAll(){
        List<ProjectEntity> projectEntityList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "select * from project";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ProjectEntity projectEntity = new ProjectEntity();
                projectEntity.setId(resultSet.getLong("id"));
                projectEntity.setName(resultSet.getString("name"));
                projectEntity.setDeployPath(StringUtil.isNullReturnEmpty(resultSet.getString("deploy_path")));
                String databaseType = resultSet.getString("database_type");
                if(databaseType!=null){
                    Common.DatabaseType databaseType1 = Common.DatabaseType.valueOf(databaseType);
                    if(databaseType1!=null){
                        projectEntity.setDatabaseType(databaseType1);
                    }
                }
                projectEntity.setDatabaseHost(resultSet.getString("database_host"));
                projectEntity.setDatabasePort(resultSet.getString("database_port"));
                projectEntity.setDatabaseName(resultSet.getString("database_name"));
                projectEntity.setDatabaseUsername(resultSet.getString("database_username"));
                projectEntity.setDatabasePassword(resultSet.getString("database_password"));
                projectEntity.setBackupNameRule(resultSet.getString("backup_name_rule"));
                projectEntity.setBackupPath(resultSet.getString("backup_path"));
                projectEntityList.add(projectEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectEntityList;
    }

    public static Integer deleteOne(Long id){
        PreparedStatement preparedStatement = null;
        Integer effectSize = null;
        try {
            String sql = "delete from project where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            effectSize = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effectSize;
    }

    public static ProjectEntity updateOne(ProjectEntity projectEntity) {
        ProjectEntity projectEntityReturn = projectEntity;
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String sql = "update project set name = ?, deploy_path = ?, database_type = ?, database_host = ?, database_port = ?, database_name = ?, database_username = ?, database_password = ?, backup_name_rule = ?, backup_path = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, StringUtil.isNullReturnEmpty(projectEntity.getName()));
            preparedStatement.setString(2, StringUtil.isNullReturnEmpty(projectEntity.getDeployPath()));
            if(projectEntity.getDatabaseType()!=null) {
                preparedStatement.setString(3, StringUtil.isNullReturnEmpty(projectEntity.getDatabaseType().value));
            }
            preparedStatement.setString(4, StringUtil.isNullReturnEmpty(projectEntity.getDatabaseHost()));
            preparedStatement.setString(5, StringUtil.isNullReturnEmpty(projectEntity.getDatabasePort()));
            preparedStatement.setString(6, StringUtil.isNullReturnEmpty(projectEntity.getDatabaseName()));
            preparedStatement.setString(7, StringUtil.isNullReturnEmpty(projectEntity.getDatabaseUsername()));
            preparedStatement.setString(8, StringUtil.isNullReturnEmpty(projectEntity.getDatabasePassword()));
            preparedStatement.setString(9, StringUtil.isNullReturnEmpty(projectEntity.getBackupNameRule()));
            preparedStatement.setString(10, StringUtil.isNullReturnEmpty(projectEntity.getBackupPath()));
            preparedStatement.setLong(11, projectEntity.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            try {
                JdbcUtil.getInstance().rollback();
                return projectEntityReturn;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectEntity;
    }

}
