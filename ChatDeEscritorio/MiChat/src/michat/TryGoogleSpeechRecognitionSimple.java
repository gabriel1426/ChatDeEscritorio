/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package michat;

import java.io.IOException;
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class TryGoogleSpeechRecognitionSimple implements GSpeechResponseListener {
	
        Chat miChat;
        String mF = "";
        final Microphone mic = new Microphone(FLACFileWriter.FLAC);
        GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
        
        public TryGoogleSpeechRecognitionSimple(Chat miChat){
            this.miChat=miChat;
        }
	public  void principal() throws IOException {
		
		//Don't use the below google api key , make your own !!! :) 
		
		duplex.setLanguage("es");
		
                 
				new Thread(() -> {
					try {
                                            
						duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
                                                
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}).start();
				
		duplex.addResponseListener(new GSpeechResponseListener() {
			String old_text = "";
			
			public void onResponse(GoogleResponse gr) {
				String output = "";
				output = gr.getResponse();
				if (gr.getResponse() == null) {
					this.old_text = miChat.txtMensajeEnviar.getText();
                                        System.out.println(old_text);
					if (this.old_text.contains("(")) {
						this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
					}
					
					this.old_text = ( miChat.txtMensajeEnviar.getText() + "\n" );
					this.old_text = this.old_text.replace(")", "").replace("( ", "");
					;
					return;
				}
				if (output.contains("(")) {
					output = output.substring(0, output.indexOf('('));
				}
				if (!gr.getOtherPossibleResponses().isEmpty()) {
					output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
				}
				
                                
				miChat.txtMensajeEnviar.setText("");
				miChat.txtMensajeEnviar.append(old_text);
				miChat.txtMensajeEnviar.append(output);
			}
                        
		}
                        
                );
	        
        
        }
      
        public void cerrar(){
            mic.close();
	    duplex.stopSpeechRecognition();
        }
	
	@Override
	public void onResponse(GoogleResponse paramGoogleResponse) {
		// TODO Auto-generated method stub
		
	}
}
