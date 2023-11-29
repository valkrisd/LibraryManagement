package org.vlad.springcourse.Library21.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vlad.springcourse.Library21.models.Administrator;

import java.util.Collection;

public class AdministratorDetails implements UserDetails {
    private final Administrator administrator;
    public AdministratorDetails(Administrator administrator) {
        this.administrator = administrator;
    }

    // список прав, которые имеет пользователь
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // получаем логин и пароль пользователя через стандартный метод (метод в модели может называться как угодно)
    @Override
    public String getPassword() {
        return this.administrator.getPassword();
    }
    @Override
    public String getUsername() {
        return this.administrator.getUsername();
    }

    // здесь реализуется логика для определения времени жизни аккаунта и тд (при необходимости)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Нужно, чтобы получать данные аутентифицированного пользователя
    public Administrator getAdministrator() {
        return this.administrator;
    }
}
