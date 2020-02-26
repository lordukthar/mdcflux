/*
 *
 *  Copyright (c) 2018-2020 Givantha Kalansuriya, This source is a part of
 *   Staxrt - sample application source code.
 *   http://staxrt.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.User;
import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.service.GreetingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * The type User controller.
 *
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  GreetingHandler greetingHandler;

  public UserController(GreetingHandler greetingHandler) {
    this.greetingHandler = greetingHandler;
  }

  /**
   * Get all users list.
   *
   * @return the list
   */
  @GetMapping("/users")
  public List<User> getAllUsers() {

    log.warn("Jallaalla");
    return Arrays.asList(new User("Jonas"));
  }

  /**
   * Gets users by id.
   *
   * @param userId the user id
   * @return the users by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/users/{id}")
  public Mono<User> getUsersById(@PathVariable(value = "id") String userId)
      throws ResourceNotFoundException {

    log.info("getUsersById {}", userId);
     MDC.put("getUsersById", userId);

    return greetingHandler.getUser(userId);

    //return ResponseEntity.ok().body(new User("Jonas"));
  }

}
