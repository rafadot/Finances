package com.finances.Finances.V1.util;

import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserUtil {
    public static User valid(UUID id, UserRepository userRepository){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent())
            throw new BadRequestException("Id do usuário inválido");

        return user.get();
    }

    public static User validEmail(String email, UserRepository userRepository){
        Optional<User> user = userRepository.findByEmail(email);

        if(!user.isPresent())
            throw new BadRequestException("Email do usuário inválido");

        return user.get();
    }
}
