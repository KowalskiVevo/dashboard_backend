package ru.urfu.dashbord.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.urfu.dashbord.jpa.entity.Users;
import ru.urfu.dashbord.jpa.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsersRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Users> user = userRepository.findByTag(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return new UserDetailsImpl(user.get());
  }
}
