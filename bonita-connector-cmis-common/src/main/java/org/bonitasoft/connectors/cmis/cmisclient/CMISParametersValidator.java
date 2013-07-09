package org.bonitasoft.connectors.cmis.cmisclient;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CMISParametersValidator {
    private Map<String, Object> parameters;

    public CMISParametersValidator(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * Validates common parameters for a CMIS query
     * username, password, url, bindingType, repository
     * @return The list of corresponding error messages
     */
    public List<String> validateCommonParameters() {
        List<String> errors = new LinkedList<String>();

        String url = (String) parameters.get("url");
        if (url == null || url.isEmpty()) {
            errors.add("URL is not set");
        }

        String bindingType = (String) parameters.get("binding_type");
        if (bindingType == null || bindingType.isEmpty()) {
            errors.add("Binding type is not set");
        } else if (!bindingType.equals("atompub") && !bindingType.equals("webservices")) {
            errors.add("Binding type should be either atompub or webservices");
        }

        String repository = (String) parameters.get("repository");
        if (repository == null || repository.isEmpty()) {
            errors.add("Repository must be set");
        }

        String username = (String) parameters.get("username");
        if (username == null || username.isEmpty()) {
            errors.add("Username is not set");
        }

        String password = (String) parameters.get("password");
        if (password == null || password.isEmpty()) {
            errors.add("Password is not set");
        }

        return errors;
    }


    /**
     * Validate specific parameters if in parameters map
     * - document : must be set
     * - folder_path : must be set
     * - destinationName : must be set
     * @return the list of errors
     */
    public List<String> validateSpecificParameters() {
        List<String> errors = new LinkedList<String>();

        if (parameters.containsKey("subfolder_name")) {
            if (checkParameterNotNull("subfolder_name")) {
                errors.add("Sub folder must be set");
            }
        }
        if (parameters.containsKey("folder_path")) {
            if (checkParameterNotNull("folder_path")) {
                errors.add("Folder path must be set");
            }
        }
        if (parameters.containsKey("document_path")) {
            if (checkParameterNotNull("document_path")) {
                errors.add("Document path must be set");
            }
        }
        if (parameters.containsKey("document_id")) {
            if (checkParameterNotNull("document_id")) {
                errors.add("Document ID must be set");
            }
        }
        if (parameters.containsKey("query")) {
            if (checkParameterNotNull("query")) {
                errors.add("Query must be set");
            }
        }
        if (parameters.containsKey("document")) {
            if (checkParameterNotNull("document")) {
                errors.add("Document must be set");
            }
        }
        if (parameters.containsKey("destinationName")) {
            if (checkParameterNotNull("destinationName")) {
                errors.add("Destination name must be set");
            }
        }
        if (parameters.containsKey("remote_document")) {
            if (checkParameterNotNull("remote_document")) {
                errors.add("Remote document must be set");
            }
        }

        return errors;
    }

    private boolean checkParameterNotNull(String parameter) {
        String parameterValue = (String) parameters.get(parameter);
        return (parameterValue == null || parameterValue.isEmpty());
    }
}