package br.com.erudio.service;

import br.com.erudio.controller.PersonController;
import br.com.erudio.data.vo.v1.PersonVOV1;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.repository.UserRepository;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {
 
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }
    
    public PersonVOV1 findById(Long id) {
	var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	logger.info("findById one Person!");
	var vo = DozerMapper.parseObject(entity, PersonVOV1.class);
	vo.add(linkTo(methodOn(PersonController.class).findByPerson(id)).withSelfRel());
	return vo;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	logger.log(Level.INFO, "findById one user by name: {0}", username);
	var user = userRepository.findByUsername(username);
	if(Objects.nonNull(user)) {
	    return user;
	} else {
	    throw new UsernameNotFoundException("Username "+username+" not found! ");
	}
    }
}