package com.elearningplatform.service.ClientServices;

import com.elearningplatform.data.model.Client;
import com.elearningplatform.data.repositories.ClientRepository;
import com.elearningplatform.dto.request.ClientReq.UpdateClientProfileRequest;
import com.elearningplatform.dto.response.ApiResponse;
import com.elearningplatform.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.elearningplatform.util.AppUtils.*;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ApiResponse updateProfile(UpdateClientProfileRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByUsername(username).orElse(null);
        if (client == null) {throw new ClientNotFoundException(CLIENT_NOT_FOUND);}
        client.setFullName(request.getFullName());
        client.setAddress(request.getAddress());
        client.setPhone(request.getPhone());
        client.setLocation(request.getLocation());
        Client savedClient = clientRepository.save(client);
        return new ApiResponse(Boolean.TRUE, PROFILE_UPDATE_SUCCESSFULLY,savedClient);
    }

    @Override
    public ApiResponse viewProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByUsername(username).orElse(null);
        if (client == null) {throw new ClientNotFoundException(CLIENT_NOT_FOUND);}
        return new ApiResponse(Boolean.TRUE, PROFILE_FOUND,client);
    }

}
