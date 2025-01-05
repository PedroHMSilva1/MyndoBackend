package com.boardhub.BoardPi.dto;


    public class LoginDTO {
        private long id;
        private String email;

        // Construtor
        public LoginDTO(long id, String email) {
            this.id = id;
            this.email = email;
        }

        // Getters e Setters
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

