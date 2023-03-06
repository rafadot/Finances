package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.user.UserRequest;
import com.finances.Finances.V1.dto.user.AllUserResponse;
import com.finances.Finances.V1.dto.user.UserResponse;
import com.finances.Finances.V1.model.EmailVerify;
import com.finances.Finances.V1.model.TypeSpent;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.model.Wallet;
import com.finances.Finances.V1.model.enums.TypeSpentColor;
import com.finances.Finances.V1.repository.TypeSpentRepository;
import com.finances.Finances.V1.repository.UserEmailVerifyRepository;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.repository.WalletRepository;
import com.finances.Finances.V1.service.interfaces.EmailService;
import com.finances.Finances.V1.service.interfaces.UserService;
import com.finances.Finances.V1.util.UserUtil;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TypeSpentRepository typeSpentRepository;
    private final PasswordEncoder encoder;
    private final EmailService emailService;
    private final UserEmailVerifyRepository userEmailVerifyRepository;

    @Override
    public UserResponse create(UserRequest userRequest) {
        Optional<User> userEmail = userRepository.findByEmail(userRequest.getEmail());

        if(userEmail.isPresent())
            throw new BadRequestException("E-mail já cadastrado");

        Optional<User> userUserName = userRepository.findByUserName(userRequest.getUserName());

        if(userUserName.isPresent())
            throw new BadRequestException("Nome de usuário já existe");

        User user = new User();

        BeanUtils.copyProperties(userRequest,user);
        user.setPassword(encoder.encode(user.getPassword()));

        Wallet wallet = Wallet.builder()
                .currentBalance(new BigDecimal("0.00"))
                .monthlyExpense(new BigDecimal("0.00"))
                .build();
        walletRepository.save(wallet);
        user.setWallet(wallet);

        TypeSpent typeSpent = TypeSpent.builder()
                .name("Outros gastos")
                .color(TypeSpentColor.GRAY)
                .totalSpent(new BigDecimal(0))
                .build();
        typeSpentRepository.save(typeSpent);
        user.getTypeSpentList().add(typeSpent);

        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user,userResponse);

        return userResponse;
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Map<String,String> deleteUser(UUID userId) {
        User user = UserUtil.valid(userId,userRepository);

        String username = user.getUserName();
        userRepository.deleteById(userId);

        Map<String,String> response = new HashMap<>();
        response.put("message",username + " deletado com sucesso!");
        return response;
    }

    @Override
    public Map<String, String> forgetPassword(String toEmail) {
        Optional<User> optUser = userRepository.findByEmail(toEmail);

        if(!optUser.isPresent()){
            throw new BadRequestException("Email não cadastrado");
        }

        User user = optUser.get();

        Random r = new Random();
        int code = r.nextInt(900000) + 100000;

        EmailVerify verify = EmailVerify.builder()
                .code(code)
                .instant(Instant.now())
                .checked(false)
                .build();
        userEmailVerifyRepository.save(verify);

        user.setEmailVerify(verify);
        userRepository.save(user);

        emailService.forgetPassword(toEmail,code);

        Map<String,String> response = new HashMap<>();
        response.put("message","Email enviado para " + toEmail + ", expira em 5 minutos!");
        return response;
    }

    @Override
    public Boolean validForgetPassword(String email, int code) {
        User user = UserUtil.validEmail(email,userRepository);

        if(user.getEmailVerify().getCode() != code)
            throw new BadRequestException("Código inválido");
        else if(ChronoUnit.MINUTES.between(user.getEmailVerify().getInstant(),Instant.now()) > 5)
            throw new BadRequestException("Código expirado");

        user.getEmailVerify().setChecked(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public Map<String, String> patchForgetPassword(String email, String password) {
        User user = UserUtil.validEmail(email,userRepository);

        if(!user.getEmailVerify().getChecked())
            throw new BadRequestException("Algo deu errado, por favor solicite um novo código");

        user.setPassword(encoder.encode(password));
        userEmailVerifyRepository.delete(user.getEmailVerify());
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message","Senha alterada com sucesso " + user.getUserName() + "!");
        return response;
    }

    @Override
    public Map<String, String> patchPassword(UUID userID, String currentPassword, String newPassword) {
        User user = UserUtil.valid(userID,userRepository);

        if(!encoder.matches(user.getPassword(),currentPassword))
            throw new BadRequestException("Senha inválida");

        user.setPassword(encoder.encode(newPassword));

        Map<String, String> response = new HashMap<>();
        response.put("message","Senha alterada com sucesso " + user.getUserName() + "!");
        return response;
    }
}
