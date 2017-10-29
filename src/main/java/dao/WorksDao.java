package dao;

import domain.Works;

import java.util.List;

public interface WorksDao {
    List<Works> getWorks(String name, int index);
}
