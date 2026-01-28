# Developer API Usage & Subscription Platform
<p align="center"> <img src="https://img.shields.io/badge/Backend-Java%2017-blue.svg"/> <img src="https://img.shields.io/badge/Framework-Spring%20Boot-brightgreen.svg"/> <img src="https://img.shields.io/badge/Security-JWT%20%7C%20Spring%20Security-orange.svg"/> <img src="https://img.shields.io/badge/Persistence-JPA%20%7C%20Hibernate-lightgrey.svg"/> <img src="https://img.shields.io/badge/Testing-JUnit%205%20%7C%20Mockito-success.svg"/> </p>

## What is this project?
This project is a backend platform that treats APIs as a product. It demonstrates how real SaaS companies (Stripe, Twilio, AWS, etc.):
- Authenticate users securely
- Organize users into organizations
- Sell API plans and subscriptions
- Generate and manage API keys
- Track API usage at scale
- Prepare usage-based billing data

⚠️ This is not a CRUD demo.
It focuses on real backend problems: security, boundaries, usage, and business rules.

## Core Features
- Authentication & Security
- JSON-based signup & login
- Password hashing
- Stateless JWT authentication
- Custom OncePerRequestFilter
- Public endpoints explicitly excluded from JWT validation

✔️ No sessions
✔️ No Basic Auth
✔️ Designed for REST APIs


## Organization Model:
- Users belong to organizations
- Organizations are the unit of billing & usage
- All subscriptions, API keys, and usage are scoped to an organization
- Organization existence is validated indirectly via dependent flows (subscription, API key generation)

## Subscription & Plans:
- Organizations subscribe to predefined plans (FREE / PRO / etc.)
- One active subscription per organization

## Plans define:
- Monthly API limits
- Pricing metadata
- Plans are auto-seeded on application startup
- Plans are treated as master/reference data, not user input
- API Key Management
- Secure API key generation

## Keys are:
- Shown only once
- Stored securely
- Revocable

## Metadata retained:
- Created time
- Revoked time
- Status
- Mimics real-world secret handling
- Usage Tracking
- Every API call increments usage

## Usage is aggregated:
- Per organization
- Per month
- Monthly limits are resolved dynamically from the active plan
- Event-driven aggregation logic
- Lazy loading & transactional boundaries handled explicitly
- Billing & Invoicing (Foundation)
- Monthly usage converted into invoices
- Invoice generation via scheduler
- Billing logic separated from usage tracking
- Designed to support future pricing strategies and overages


## How to Run
- Clone the repository
- Open the project in IntelliJ IDEA
- Ensure Java 17 is configured
- Run ApiPlatformApplication
- Use Postman to test the APIs
- API Testing Flow (Recommended)
- Signup → receive JWT
- Login → verify credentials
- Create Organization
- Subscribe to a Plan
- Generate API Key
- Track Usage
- Review Billing Data
- Test security edge cases

## What This Project Demonstrates?
- Practical Spring Boot experience
- Clean separation of concerns
- Real-world API design
- Secure authentication patterns
- Thoughtful domain modeling
- Awareness of common backend pitfalls (lazy loading, transactions, security filters)

## Future Improvements
- API-key-based authentication middleware
- Rate limiting at filter level
- Organization roles and permissions
- Admin APIs for plan management
- External billing provider integration

## Final Note : This project is not meant to be “perfect” or over-engineered.
