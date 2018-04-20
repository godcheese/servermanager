package com.gioov.entity;

import com.gioov.common.Common;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/16 11:46
 */
public class ProjectEntity {

    private Long id;
    private String name;
    private String deployPath;
    private Common.DatabaseType databaseType;
    private String databaseHost;
    private String databasePort;
    private String databaseName;
    private String databaseUsername;
    private String databasePassword;
    private String backupNameRule;
    private String backupPath;

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deployPath='" + deployPath + '\'' +
                ", databaseType=" + databaseType +
                ", databaseHost='" + databaseHost + '\'' +
                ", databasePort='" + databasePort + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", databaseUsername='" + databaseUsername + '\'' +
                ", databasePassword='" + databasePassword + '\'' +
                ", backupNameRule='" + backupNameRule + '\'' +
                ", backupPath='" + backupPath + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeployPath() {
        return deployPath;
    }

    public void setDeployPath(String deployPath) {
        this.deployPath = deployPath;
    }

    public Common.DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(Common.DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public void setDatabaseHost(String databaseHost) {
        this.databaseHost = databaseHost;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getBackupNameRule() {
        return backupNameRule;
    }

    public void setBackupNameRule(String backupNameRule) {
        this.backupNameRule = backupNameRule;
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }
}
