//
//  ContentView.swift
//  Nothing
//
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        ZStack {
            Color.init(red: 238, green: 238, blue: 238)
                .edgesIgnoringSafeArea(.all)
            Text("Nothing")
                .font(Font.custom("questrial_regular", size: 24.0))
                .lineLimit(0)
                .multilineTextAlignment(.center)
                .padding()
                .onTapGesture(count: 1, perform: {
                    print("Nothing click")
                })
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
