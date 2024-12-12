package br.com.spotiparty.login

import br.com.spotiparty.authentication.JwtService
import br.com.spotiparty.user.User
import br.com.spotiparty.user.UserRepository
import br.com.spotiparty.user.exception.UserNotFoundException
import br.com.spotiparty.user.role.RoleRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserSignOnUseCase(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) : UserDetailsService {

    fun registerUser(userDTO: UserDTO) = userRepository.save(
        User(
            username = userDTO.username,
            password = passwordEncoder.encode(userDTO.password),
            email = userDTO.email,
            cellphone = userDTO.cellphone,
            roles = roleRepository.findAll(),
        )
    )

    fun login(signInDTO: SignInDTO): SignInResponseDTO {
        return userRepository.findByLoginParameters(signInDTO.username)
            ?.apply {
                if (!passwordEncoder.matches(
                        signInDTO.password,
                        this.password
                    )
                ) throw UserNotFoundException("Wrong password")
            }
            ?.let(jwtService::generateToken)
            ?.let(::SignInResponseDTO) ?: throw UserNotFoundException("User with login credentials not found.")
    }

    override fun loadUserByUsername(username: String?): User {
        return userRepository.findByUsername(username)
            ?: throw UserNotFoundException("Username not found: $username")
    }
}