package rs.ac.uns.ftn.gateway.gateway.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.gateway.gateway.model.UniqueVisitor;

import java.util.Optional;

@Repository
public interface UniqueVisitorRepository extends CrudRepository<UniqueVisitor, String> {

    Optional<UniqueVisitor> findAllByAddressAndBrowserAndTimestamp(String address, String browser, Long timestamp);

    Optional<UniqueVisitor> findFirstByAddress(String address);

}