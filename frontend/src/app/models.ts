export interface UserData {
    login: string;
    id: string;
    html_url: string;
    avatar_url: string;
}

export interface GoogleData {
    email: string;
    picture: string;
    name: string;
    sub: string;
}

export interface Contact {
	email: string;
	title: string;
	text: string;
    attachment: File;
}

export interface Club {
    name: string;
    id: string;
    heroImage: string;
}

export interface Stats {
    ownGoals: string;
    yellowCards: string;
    redCards: string;
    minutesPlayed: string;
    matches: string;
    goals: string;
    assists: string;
    // clean sheet = toNil
    // will need to hide this stats if player is not a goalkeeper
    toNil: string;
    concededGoals : string;
    isGoalkeeper : boolean;
}

export interface PlayerSQL {
    id: string;
    name: string;
}