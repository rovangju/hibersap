package org.hibersap.configuration.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.hibersap.session.SapErrorInterceptor;
import org.junit.Test;

public class HibersapConfigTest
{
    @Test
    public void create()
        throws Exception
    {
        HibersapConfig config = new HibersapConfig();

        config.addSessionManager( "Sm1" ).setContext( "org.hibersap.execution.jco.JCoContext" )
            .setJcaConnectionFactory( "java:/eis/sap/A12" ).setProperty( "key1", "value1" ).setProperty( "key2",
                                                                                                         "value2" )
            .addAnnotatedClass( Integer.class ).addAnnotatedClass( String.class );

        config.addSessionManager( "Sm2" ).setContext( "org.hibersap.execution.jca.JCAContext" )
            .setJcaConnectionFactory( "java:/eis/sap/B34" ).setProperty( "key3", "value3" ).setProperty( "key4",
                                                                                                         "value4" )
            .addAnnotatedClass( String.class ).addAnnotatedClass( Integer.class ).addInterceptor( SapErrorInterceptor.class );

        assertProperties( config, "Sm1", "org.hibersap.execution.jco.JCoContext", "java:/eis/sap/A12", "key1",
                          "value1", "key2", "value2" );
        assertProperties( config, "Sm2", "org.hibersap.execution.jca.JCAContext", "java:/eis/sap/B34", "key3",
                          "value3", "key4", "value4" );

        Set<String> interceptors = config.getSessionManager( "Sm2" ).getInterceptorClasses();
        assertEquals( 1, interceptors.size() );
        assertEquals( SapErrorInterceptor.class.getName(), interceptors.iterator().next() );

    }

    private void assertProperties( HibersapConfig config, String smName, String smContext, String smConnFact,
                                   String smPropKey1, String smPropVal1, String smPropKey2, String smPropVal2 )
    {
        SessionManagerConfig sessionManager = config.getSessionManager( smName );
        assertEquals( smName, sessionManager.getName() );
        assertEquals( smContext, sessionManager.getContext() );
        assertEquals( smConnFact, sessionManager.getJcaConnectionFactory() );
        assertEquals( 2, sessionManager.getProperties().size() );
        assertEquals( smPropVal1, sessionManager.getProperty( smPropKey1 ) );
        assertEquals( smPropVal2, sessionManager.getProperty( smPropKey2 ) );
        Set<String> annotatedClasses = sessionManager.getClasses();
        assertEquals( 2, annotatedClasses.size() );
        assertTrue( "SessionManager " + smName, annotatedClasses.contains( String.class.getName() ) );
        assertTrue( annotatedClasses.contains( Integer.class.getName() ) );
    }
}
