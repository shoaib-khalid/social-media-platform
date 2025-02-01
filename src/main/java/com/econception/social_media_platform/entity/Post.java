package com.econception.social_media_platform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
