package com.akshay.automationexecrices.utils;

import java.util.*;

public class CommonMethods {

        private static final Random random = new Random();

        // Email
        private static final String EMAIL_PREFIX = "testuser_";
        private static final String EMAIL_DOMAIN = "@yopmail.com";
        private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        private static final int RANDOM_STRING_LENGTH = 4;

        // Password
        private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+=-{}[]|:;<>,.?/";
        private static final int PASSWORD_LENGTH = 10;

        // Name
        private static final List<String> INDIAN_FIRST_NAMES = Arrays.asList(
                "Akshay", "Priya", "Rahul", "Sneha", "Ankit", "Riya", "Vikram", "Asha",
                "Saurabh", "Meena", "Ramesh", "Pooja", "Kunal", "Deepa", "Arjun", "Neha",
                "Amit", "Divya", "Nikhil", "Komal"
        );

        private static final List<String> INDIAN_SURNAMES = Arrays.asList(
                "Sharma", "Verma", "Patel", "Reddy", "Yadav", "Singh", "Kumar", "Mishra",
                "Joshi", "Thakur", "Jain", "Aggarwal", "Naidu", "Gupta", "Bansal"
        );

        // Address (State to City Map)
        private static final Map<String, List<String>> STATE_CITY_MAP = new HashMap<>() {{
            put("Uttar Pradesh", Arrays.asList("Lucknow", "Kanpur", "Varanasi"));
            put("Maharashtra", Arrays.asList("Mumbai", "Pune", "Nagpur"));
            put("Karnataka", Arrays.asList("Bangalore", "Mysore", "Hubli"));
            put("Tamil Nadu", Arrays.asList("Chennai", "Coimbatore", "Madurai"));
            put("West Bengal", Arrays.asList("Kolkata", "Asansol", "Durgapur"));
            put("Telangana", Arrays.asList("Hyderabad", "Warangal", "Karimnagar"));
            put("Delhi", Arrays.asList("New Delhi", "Dwarka", "Rohini"));
            put("Gujarat", Arrays.asList("Ahmedabad", "Surat", "Vadodara"));
            put("Madhya Pradesh", Arrays.asList("Indore", "Bhopal", "Jabalpur"));
            put("Rajasthan", Arrays.asList("Jaipur", "Udaipur", "Jodhpur"));
        }};

        // Streets
        private static final List<String> INDIAN_STREETS = Arrays.asList(
                "MG Road", "Ring Road", "Sarojini Nagar", "Laxmi Bai Marg", "Station Road",
                "Subhash Chawk", "Netaji Subhash Marg", "Rajpath", "Nehru Place", "Gandhi Road"
        );

        public static String generateRandomEmail() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
                sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
            }
            return EMAIL_PREFIX + sb + EMAIL_DOMAIN;
        }

        public static String generateRandomPassword() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < PASSWORD_LENGTH; i++) {
                sb.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
            }
            return sb.toString();
        }

        public static String generateRandomIndianName() {
            String firstName = INDIAN_FIRST_NAMES.get(random.nextInt(INDIAN_FIRST_NAMES.size()));
            String lastName = INDIAN_SURNAMES.get(random.nextInt(INDIAN_SURNAMES.size()));
            return firstName + " " + lastName;
        }

        public static String generateTestPhoneNumberStartingWith1() {
            StringBuilder phone = new StringBuilder("1");
            for (int i = 0; i < 9; i++) {
                phone.append(random.nextInt(10));
            }
            return phone.toString();
        }

        public static String generateRandomStreet() {
            return INDIAN_STREETS.get(random.nextInt(INDIAN_STREETS.size()));
        }

        public static String[] generateValidStateAndCityPair() {
            List<String> states = new ArrayList<>(STATE_CITY_MAP.keySet());
            String selectedState = states.get(random.nextInt(states.size()));
            List<String> cities = STATE_CITY_MAP.get(selectedState);
            String selectedCity = cities.get(random.nextInt(cities.size()));
            return new String[]{selectedState, selectedCity};
        }

    public static String getEmail(){
            return CommonMethods.generateRandomEmail();
    }

    public static String getName() {
        return generateRandomIndianName();
    }

    public static String getPassword() {
        return generateRandomPassword();
    }

    public static String getPhone() {
        return generateTestPhoneNumberStartingWith1();
    }

    public static String getStreet() {
        return generateRandomStreet();
    }

    // Since state and city come together, return an array or create separate methods if needed
    public static String[] getStateCity() {
        return generateValidStateAndCityPair();
    }

    // For convenience, you can add separate getters if you want just state or city:
    public static String getState() {
        return generateValidStateAndCityPair()[0];
    }

    public static String getCity() {
        return generateValidStateAndCityPair()[1];
    }


}
