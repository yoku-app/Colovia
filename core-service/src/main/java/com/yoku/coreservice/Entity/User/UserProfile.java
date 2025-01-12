package com.yoku.coreservice.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "main_focus")
    private Focus focus;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Embedded
    private OnboardingCompletion onboardingCompletion;

    // Embedded class for Onboarding Completion
    @Embeddable
    @Getter
    @Setter
    public static class OnboardingCompletion {

        @Column(name = "respondent_onboarding_completion")
        private Date respondent;

        @Column(name = "creator_onboarding_completion")
        private Date creator;

        @Column(name = "core_onboarding_completion")
        private Date core;

        public OnboardingCompletion() {

        }
    }

    public enum Focus {
        RESPONDENT,
        CREATOR,
        HYBRID
    }

    public UserProfile() {

    }
}
