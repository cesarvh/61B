// A client that uses the synthesizer package to replicate a plucked guitar string sound
public class GuitarHeroLite {
      public static void main(String[] args) {

          // create two guitar strings, for concert A and C
          double CONCERT_A = 440.0;
          double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0); 
          double CONCERT_G = 110;
          double CONCERT_I = 220;
          double CONCERT_V = 880;

          synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
          synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);
          synthesizer.GuitarString stringG = new synthesizer.GuitarString(CONCERT_G);
          synthesizer.GuitarString stringI = new synthesizer.GuitarString(CONCERT_I);
          synthesizer.GuitarString stringV = new synthesizer.GuitarString(CONCERT_V);



          while (true) {

              // check if the user has typed a key; if so, process it   
              if (StdDraw.hasNextKeyTyped()) {
                  char key = StdDraw.nextKeyTyped();
                  if      (key == 'a') { stringA.pluck(); }
                  else if (key == 'c') { stringC.pluck(); }
                  else if (key == 'g') {stringG.pluck();}
                  else if (key == 'i') {stringI.pluck();}
                  else if (key == 'v') {stringV.pluck();}
              }

              // compute the superposition of samples
              double sample = stringA.sample() + stringC.sample() +
               stringG.sample() + stringI.sample() + stringV.sample() ;


  
              // play the sample on standard audio
              // note: this is just playing the double value YOUR GuitarString
              //       class is generating. 
              StdAudio.play(sample);

  
              // advance the simulation of each guitar string by one step   
              stringA.tic();
              stringC.tic();
              stringG.tic();
              stringI.tic();
              stringV.tic();
          }
       }
  }

