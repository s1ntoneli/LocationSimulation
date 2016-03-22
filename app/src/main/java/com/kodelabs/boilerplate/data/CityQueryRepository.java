package com.kodelabs.boilerplate.data;

import com.kodelabs.boilerplate.domain.repository.Repository;

/**
 * Created by lixindong on 16/3/17.
 */
public interface CityQueryRepository extends Repository {
    int MODE_NORMAL = 0;
    int MODE_RANDOM = 1;
    int MODE_HOT = 2;
    String[] query(String keyword, int page, int pageSize);
    String[] queryRandom(int number);
    String[] queryHot(int pageSize, int page);
}
