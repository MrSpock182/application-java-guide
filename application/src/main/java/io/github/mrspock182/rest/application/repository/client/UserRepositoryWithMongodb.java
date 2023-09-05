package io.github.mrspock182.rest.application.repository.client;

import io.github.mrspock182.rest.application.repository.orm.UserOrm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryWithMongodb extends MongoRepository<UserOrm, String> {
}