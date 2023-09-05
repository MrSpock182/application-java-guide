package io.github.mrspock182.javapp.springrest.repository.client;

import io.github.mrspock182.javapp.springrest.repository.orm.UserOrm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryWithMongodb extends MongoRepository<UserOrm, String> {
}