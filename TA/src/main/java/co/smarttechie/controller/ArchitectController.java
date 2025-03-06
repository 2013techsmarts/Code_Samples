package co.smarttechie.controller;

import co.smarttechie.model.Requirement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ArchitectController {

    @GetMapping("/technical-architect")
    public String getTechnicalArchitectInfo(Model model) {
        // List of requirements with titles and descriptions
        List<Requirement> requirements = List.of(
                new Requirement("Designing System Architecture", "Develop architectural blueprints that outline the structure and components of IT solutions, ensuring they meet business requirements and future needs. This involves defining system components, data flow, and integration points to create a cohesive and scalable architecture."),
                new Requirement("Stakeholder Collaboration", "Work closely with business leaders, project managers, and developers to gather requirements and align the architecture with business goals. Effective communication and collaboration ensure that the architecture meets both technical and business objectives."),
                new Requirement("Overseeing Development", "Guide the development team during the implementation phase, ensuring adherence to the architectural design and best practices. This involves providing technical oversight, addressing issues, and ensuring the project stays on track."),
                new Requirement("Conducting Feasibility Studies", "Be ready to create POCs, MVPs. Assess the viability of proposed solutions by analyzing technical constraints and aligning them with business objectives. This involves evaluating the practicality and potential impact of different approaches to ensure they are achievable and beneficial."),
                new Requirement("Security and Compliance", "Implement robust security measures and ensure that the system complies with industry standards and regulatory requirements. This includes incorporating security best practices and conducting regular audits to maintain compliance."),
                new Requirement("Mentoring and Training", "Provide guidance and training to team members, fostering a culture of continuous learning and technical improvement. \n" +
                        "This helps in building a skilled team that can effectively implement and maintain the system architecture."),
                new Requirement("Documentation", "Maintain comprehensive documentation of the system architecture, design decisions, and technical specifications throughout the project. Clear documentation ensures that the architecture can be understood, maintained, and modified as needed."),
                new Requirement("Application Support and Monitoring", "Have patience to check the logs. Monitor and optimize the performance of implemented solutions to ensure they operate efficiently and meet performance benchmarks. \n" +
                        "This involves regular performance assessments and fine-tuning of system components."),
                new Requirement("Innovation and Trends", "Stay updated with the latest technological trends and advancements to provide innovative solutions that enhance business processes. \n" +
                        "This proactive approach helps in leveraging new technologies to improve system functionality and efficiency. Attend online meetups/live events to understand how industry is adopting new trends."),
                new Requirement("Hiring , Firing, Performance Reviews", "Collaborate with hiring team, conduct interviews to onboard a team. Some times you need to take hard decisions to fire also. \n" +
                        "Sometimes we need to give performance reviews."),
                new Requirement("Conflict Resolution", "Have good rapport with team, resolve conflicts if the team have some conflicts. Have empathy towards team members."),
                new Requirement("Continuous learning mindset", "Have continuous learning mindset. Engage with other Architects to discuss and learn. Contribute to tech forums. Upskill by doing relevant certifications. Lead by example."),
                new Requirement("Presentation and communication skills", "Have mindset to explain the things on white board (digital/physical), Have good listening skills. Coordinate with Architecture Review Board, Technology Review Board,\n" +
                        "Tech Risk team to get approval for architecture, tech stack etc. Engage client calls."),
                new Requirement("Pre sales Support", "Support presales. Provide support to RFPs. Creation of WBS."),
                new Requirement("Project Management Skills", "Experience with project management methodologies (e.g., Agile, Scrum) and tools. Ability to manage multiple projects, allocate resources, and adhere to timelines."),
                new Requirement("Negotiation", "Proficiency in negotiating project requirements, timelines, and resources with stakeholders to achieve mutually beneficial outcomes."),
                new Requirement("Time Management", "Effective at managing time and priorities to handle multiple tasks and projects simultaneously while meeting deadlines."),
                new Requirement("Domain Knowledge", "Having or obtaining domain knowledge is essential to provide better architectures.")
                );

        // Add the requirements to the model
        model.addAttribute("tags", requirements);

        // Return the name of the Thymeleaf template
        return "architect-info";
    }

    @GetMapping("/tech-details")
    public String getTechDetailsScreen(Model model) {
        // Create a list of requirements
        List<Requirement> requirements = List.of(
                new Requirement("Be Hands-On and Versatile", "Master at least one programming language (e.g., Node.js, Python, .NET). Gain familiarity with other languages and frameworks."),
                new Requirement("Architectural Knowledge", "Understand architectures like N-tier, Monolith, Microservices, Event-Driven, and Micro Frontends. Evaluate tradeoffs to choose the right architecture for the project."),
                new Requirement("TDD/BDD and Test Coverage", "Practice Test-Driven Development (TDD) and Behavior-Driven Development (BDD). Encourage high test coverage with unit and integration tests."),
                new Requirement("Cloud and Containerization", "Understand cloud architectures and services (e.g., AWS, Azure, GCP). Work with Docker and Kubernetes for container orchestration."),
                new Requirement("CI/CD and DevOps Tools", "Use tools like Jenkins, Terraform, GitLab, and SonarQube. Implement CI/CD pipelines for automated testing and deployments."),
                new Requirement("Database Expertise", "Understand RDBMS and NoSQL databases (e.g., MongoDB, Cassandra). Apply tuning techniques like sharding, partitioning, and normalization."),
                new Requirement("Caching Technologies", "Use caching tools like Redis and Hazelcast."),
                new Requirement("API Knowledge", "Understand API styles like SOAP, REST, and GraphQL. Work with API gateways like APIGEE and cloud-specific gateways."),
                new Requirement("Messaging Systems", "Use messaging systems like RabbitMQ and Apache Kafka."),
                new Requirement("Application Security", "Follow OWASP guidelines and manage vulnerabilities. Understand encryption, hashing, and digital signatures."),
                new Requirement("Compliance Protocols", "Adhere to GDPR, HIPAA, and PCI compliance standards."),
                new Requirement("Observability Tools", "Use tools like ELK Stack, Datadog, Prometheus, and Grafana. Set up structured logging, alerts, and monitoring."),
                new Requirement("Agile Practices", "Participate in Agile ceremonies like sprint planning and retrospectives. Collaborate using tools like JIRA, Confluence, and Wiki."),
                new Requirement("Showcase Your Work", "Present achievements in big room or PI planning sessions. Market your work and highlight OKRs (Objectives and Key Results)."),
                new Requirement("AI and GenAI", "Understand AI, LLMs, and tools like GitHub Copilot."),
                new Requirement("Design Patterns", "Apply design patterns like CQRS, Circuit Breaker, and 12-Factor App.")
        );

        // Add the list to the model
        model.addAttribute("skills", requirements);

        // Return the Thymeleaf template name
        return "tech-details-screen";
    }

    @GetMapping("/ref-details")
    public String getRefDetailsScreen(Model model) {
        // Create a list of requirements
        List<Requirement> requirements = List.of(
                new Requirement("https://www.thoughtworks.com/en-us/radar", "Technology Radar"),
                new Requirement("https://www.infoq.com/", "InfoQ"),
                new Requirement("https://dzone.com/", "DZone"),
                new Requirement("https://netflixtechblog.com/", "Netflix TechBlog"),
                new Requirement("https://tech.walmart.com/content/walmart-global-tech/en_us/blog/post.html", "Walmart TechBlog"),
                new Requirement("https://www.allthingsdistributed.com/", "All Things Distributed"),
                new Requirement("https://martinfowler.com/", "Martin Fowler"),
                new Requirement("https://www.linkedin.com/blog/engineering", "LinkedIn TechBlog"),
                new Requirement("https://nofluffjuststuff.com/", "No Fluff Just Stuff"),
                new Requirement("https://www.meetup.com/", "Local Tech Meetups"),
                new Requirement("https://www.eventbrite.com/", "Local Tech Events"),
                new Requirement("https://highscalability.com/", "High Scalability Blog"),
                new Requirement("https://stackoverflow.blog/engineering/", "Stack Overflow TechBlog"),
                new Requirement("https://www.nvidia.com/gtc/", "Nvidia AI Conference"),
                new Requirement("https://developersummit.com/", "Developer Summit"),
                new Requirement("https://bytebytego.com/", "Byte Byte Go"),
                new Requirement("https://www.algoexpert.io/systems/product", "Algo Expert to revise skills. Sometimes you will get all the courses for $100. I personally go through this to refresh design skills."),
                new Requirement("https://reinvent.awsevents.com/", "AWS Re:Invent"),
                new Requirement("https://ignite.microsoft.com/en-US/home", "Microsoft Ignite"),
                new Requirement("https://cloud.withgoogle.com/next/25", "Google Next"),
                new Requirement("https://www.coursera.org/", "Coursera"),
                new Requirement("https://www.udemy.com/", "Udemy. You will get courses for $10. Most of the times they will be running promotions. Some organizations will have business accounts through which you can learn any course for free."),
                new Requirement("https://www.freecodecamp.org/news/", "FreeCodeCamp. Free courses.")
        );

        // Add the list to the model
        model.addAttribute("refs", requirements);

        // Return the Thymeleaf template name
        return "ref-details-screen";

    }

    @GetMapping("/thank-you")
    public String getThankYouScreen(Model model) {
        // Create a list of requirements
        List<Requirement> requirements = List.of(
                new Requirement("https://topmate.io/siva_janapati", "I hope you got insights on what is required to become a " +
                        "Technical Architect. If you want more guidance, feel free to book 1-on-1 discussion with me.")
        );

        // Add the list to the model
        model.addAttribute("thanks", requirements);

        // Return the Thymeleaf template name
        return "thank-you-screen";

    }
}
