package com.kodelabs.boilerplate.data;

import com.kodelabs.boilerplate.domain.repository.Repository;

/**
 * Created by lixindong on 16/3/17.
 */
public interface CityQueryRepository extends Repository {
    String[] query(String keyword, int page, int pageSize);
    String[] queryRandom(int number);
    String[] queryHot(int number);
}
