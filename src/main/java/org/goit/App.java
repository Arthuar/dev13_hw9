package org.goit;

import org.goit.connection_properties.PathConstants;
import org.goit.criteria.CriteriaExecutor;
import org.goit.flyway.MigrationExecutor;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MigrationExecutor executor = new MigrationExecutor(PathConstants.DB_CONNECTION_PROPERTIES_FILE_PATH);
        executor.executeMigrations();
        CriteriaExecutor ce = new CriteriaExecutor();
        //get entities according to hw instructions
        ce.getNeededResidents();
        ce.close();
    }
}
