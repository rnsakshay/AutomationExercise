package com.akshay.automationexecrices.utils;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoogleSheetManager {

    private static final String APPLICATION_NAME = "AutomationExercise";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String SPREADSHEET_ID = "12zhrqBn16Bp4Oa5X9w10EYAKUgt0LWY97VWb88313Co"; // Replace with your Sheet ID
    private static final String RANGE = "LoginUserInfo!A:B"; // Adjust as needed

    public static List<List<Object>> readSheetData() throws Exception {
        FileInputStream serviceAccountStream = new FileInputStream("src/test/resources/credentials.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));


        Sheets service = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials)) // ✅ FIXED HERE
                .setApplicationName(APPLICATION_NAME)
                .build();

        ValueRange response = service.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();

        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
            return Collections.emptyList();
        }
        return values;
    }

    public static List<String> appendRowData(List<String> rowData) throws Exception {
        FileInputStream serviceAccountStream = new FileInputStream("src/test/resources/credentials.json");

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        Sheets service = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Convert List<String> to List<List<Object>> for the API
        List<Object> rowDataObj = new ArrayList<>(rowData);
        List<List<Object>> values = Collections.singletonList(rowDataObj);

        ValueRange body = new ValueRange().setValues(values);

        // Change range to just the column range — without specifying row numbers
        String range = "NewUserInfo!A:I"; // Or A:Z depending on how many columns

        AppendValuesResponse response = service.spreadsheets().values()
                .append(SPREADSHEET_ID, range, body)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();

     //   System.out.println("New row appended at: " + response.getUpdates().getUpdatedRange());
        return rowData;
    }
}