package za.co.kemtech.agrigate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.kemtech.agrigate.entity.Client;
import za.co.kemtech.agrigate.exceptions.ClientNotFoundException;
import za.co.kemtech.agrigate.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public Client findById(Long clientId){
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (!optionalClient.isPresent()){
            throw new ClientNotFoundException("Client " + clientId + " Not Found");
        }
        return optionalClient.get();
    }

    public Client add(Client client){
        return clientRepository.save(client);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }
}
