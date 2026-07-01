package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.user.RequestUserDTO;
import com.example.springboot_4_initial.dto.user.ResponseUserDTO;

import java.util.List;

public interface IUserSevice {
    public abstract List<ResponseUserDTO> getAllUsers(boolean status);
    public abstract ResponseUserDTO getUserById(Long idUser);
    public abstract ResponseUserDTO createUser(RequestUserDTO requestUserDTO, boolean isRecruiter);
    public abstract ResponseUserDTO updateUser(Long idUser);
    public abstract void deleteUser(Long idUser);
}
