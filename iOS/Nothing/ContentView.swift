//
//  ContentView.swift
//  Nothing
//
//  Created by Sebastian Tellez on 06/10/20.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
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

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
