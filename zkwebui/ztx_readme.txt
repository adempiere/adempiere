ztx.bat

Extracts and copies the default theme from the zk jars to a specified folder.

Usage: ztx lib_path theme_name
       lib_path The path where zul.jar, zkex.jar, and/or zkmax.jar are located
       theme_name The name of the new theme based on the default theme (breeze)
       
In an external tool configuration:

Location: ${workspace_loc:/adempiereTrunk/zkwebui/ztx.bat}
Working Directory: ${workspace_loc:/adempiereTrunk/zkwebui}
Arguments: ${workspace_loc:/adempiereTrunk/zkwebui/WEB-INF/lib} 
           ${workspace_loc:/adempiereTrunk/zkwebui/theme}\newcopy
           
In the Refresh tab, select refresh selected resource and select only the zkwebui/theme folder.       

Rename the folder newcopy and register the theme.

