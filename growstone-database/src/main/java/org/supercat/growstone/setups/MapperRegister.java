package org.supercat.growstone.setups;

public class MapperRegister {
    private MapperRegister() {}

    public static void addMappers() {
        DBSqlExcutor.sqlSessionFactory.getConfiguration().addMappers(
                "org.supercat.growstone.mappers"
        );
    }
}
