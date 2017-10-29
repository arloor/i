package service;

import domain.Works;

import java.util.List;

public interface WorksService {
    List<Works> getWorks(String name, int index);
}
